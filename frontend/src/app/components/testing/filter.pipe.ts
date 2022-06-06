import { Pipe, PipeTransform } from '@angular/core';  
import { Test } from 'src/app/models/test.model';

@Pipe({
    name: 'filter'
  })
  export class FilterPipe implements PipeTransform {
    transform(value: Test[], searchValue): any {
      if (!searchValue) return value;
      return value.filter((v) => 
      v.title.toLowerCase().indexOf(searchValue.toLowerCase()) > -1 || 
      v.title.toLowerCase().indexOf(searchValue.toLowerCase()) > -1)
    }
  }