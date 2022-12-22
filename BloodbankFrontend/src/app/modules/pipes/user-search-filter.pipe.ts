import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'userSearchFilter'
})
export class UserSearchFilterPipe implements PipeTransform {

  transform(list: any[], filterText: string): any {
    return list ? list.filter(item => item.name.search(new RegExp(filterText, 'i')) > -1) : [];
  }

}
