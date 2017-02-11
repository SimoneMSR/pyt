package com.pyt.service;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import com.pyt.model.Language;
import com.pyt.service.bean.Email;
import com.pyt.service.bean.EmailTemplates;
import com.pyt.service.bean.FreeMarkerDataContainer;

import Enums.EmailTemplateName;
import exception.TemplateNotFoundException;
import freemarker.cache.NullCacheStorage;
import freemarker.cache.TemplateLoader;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


@Stateless
public class FreeMarkerService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Inject
	private EmailTemplatesService emailTemplatesService;

	public Configuration getFreeMarkerConfiguration(Map<String, String> includedTemplate) {

		Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
		cfg.setObjectWrapper(new BeansWrapper(Configuration.VERSION_2_3_23));
		cfg.setCacheStorage(new NullCacheStorage());
		cfg.setLocalizedLookup(false);
		cfg.setTemplateLoader(new TemplateLoader() {

			@Override
			public Reader getReader(Object templateSource, String encoding) throws IOException {
				return new StringReader(includedTemplate.get(templateSource));
			}

			@Override
			public long getLastModified(Object templateSource) {
				// faccio il caching con varnish
				return 0;
			}

			@Override
			public Object findTemplateSource(String name) throws IOException {
				return name;
			}

			@Override
			public void closeTemplateSource(Object templateSource) throws IOException {
			}
		});

		return cfg;
	}

	public Email compileForEmail(Language language, EmailTemplateName tname,
			FreeMarkerDataContainer data) throws TemplateNotFoundException, IOException, TemplateException {

		Map<String, String> includedTemplates = new HashMap<>();

		EmailTemplates emailTemplates = emailTemplatesService.getTemplate();
		String templateSubject = emailTemplates.getTemplatesubjects().get(tname);
		String templateBody = emailTemplates.getTemplatebodies().get(tname);
		String translations = emailTemplates.getTranslations().get(language);
		includedTemplates.put("header.ftl", emailTemplates.getHeader());
		includedTemplates.put("footer.ftl", emailTemplates.getFooter());

		if (templateBody == null)
			throw new TemplateNotFoundException("Template not found  lang="
					+ language.getCode() + " tname=" + tname + "]");

		return compileForEmail(templateSubject, templateBody, translations, data, includedTemplates);
	}

	public Email compileForEmail(String templateSubject, String templateBody, String translations,
			FreeMarkerDataContainer data, Map<String, String> includedTemplates)
					throws TemplateNotFoundException, IOException, TemplateException {
		logger.debug("Load translation...");

		try {
			Object translation = new Yaml().load(translations);
			data.setTranslation(translation);
		} catch (Throwable e) {
			logger.error("Can't load translation: " + translations, e);
		}

		return compileForEmail(templateSubject, templateBody, data, includedTemplates);

	}

	public Email compileForEmail(String templateSubject, String templateBody, FreeMarkerDataContainer data,
			Map<String, String> includedTemplates) throws IOException, TemplateException {

		Configuration configuration = getFreeMarkerConfiguration(includedTemplates);
		Email out = new Email();

		Template template = new Template("template", templateSubject, configuration);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(baos));
		template.process(data, writer);
		writer.flush();
		writer.close();
		out.subject = baos.toString();
		baos.close();

		template = new Template("template", templateBody, configuration);
		baos = new ByteArrayOutputStream();
		writer = new BufferedWriter(new OutputStreamWriter(baos));
		template.process(data, writer);
		writer.flush();
		writer.close();
		out.body = baos.toString();
		baos.close();

		return out;
	}

}
