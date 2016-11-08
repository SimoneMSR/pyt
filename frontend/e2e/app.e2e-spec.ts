import { PytPage } from './app.po';

describe('pyt App', function() {
  let page: PytPage;

  beforeEach(() => {
    page = new PytPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
