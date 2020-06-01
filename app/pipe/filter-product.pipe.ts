import { Pipe, PipeTransform } from '@angular/core';
import { Product } from '../Model/Product';

@Pipe({
  name: 'filterProduct'
})
export class FilterProductPipe implements PipeTransform {

  
 /* transform(products:Product[], SearchTerm:String): Product[] {
    if(!products || !SearchTerm)
    {
      return products;
    }
    return products.filter(product=>product.productName.toLocaleLowerCase().match(SearchTerm.toLocaleLowerCase()));
    return products.filter(product=>product.productBrand.toLocaleLowerCase().match(SearchTerm.toLocaleLowerCase()));
  }*/
  transform(value: any, args?: any): any {

    if(!value)return null;
    if(!args)return value;

    args = args.toLowerCase();

    return value.filter(function(item){
        return JSON.stringify(item).toLowerCase().includes(args);
    });
}
  /*transform(value:Product[], SearchTerm:String) {       
    if(!SearchTerm)
    return value;
    let filteredValues:any=[];      
    for(let i=0;i<value.length;i++){
        if(value[i].productName.toLowerCase().includes(SearchTerm.toLowerCase())){
            filteredValues.push(value[i]);
        }
    }
    return filteredValues;
}*/
}
