import { Pipe, PipeTransform } from '@angular/core';

@Pipe({name: 'tagName'})
export class TagNamePipe implements PipeTransform {
  transform(tag): string {
    return tag.name;
  }
}