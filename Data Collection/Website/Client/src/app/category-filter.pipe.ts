import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'categoryFilter'
})
export class CategoryFilterPipe implements PipeTransform {

  transform(value: any, args: number[]): any {
    let category = args[0];
    console.log("category is: " + args);
    return value.filter(item => {return item.categoryId == category});
  }

}
