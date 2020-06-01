import { Pipe, PipeTransform } from '@angular/core';
import { MerchantDetails } from '../Model/MerchantDetails';

@Pipe({
  name: 'filterMerchant'
})
export class FilterMerchantPipe implements PipeTransform {

 /* transform(merchants:MerchantDetails[], SearchTerm:String): MerchantDetails[] {
    if(!merchants || !SearchTerm)
    {
      return merchants;
    }
    return merchants.filter(merchant=>merchant.name.toLocaleLowerCase().match(SearchTerm.toLocaleLowerCase()));
  }*/
  transform(value: any, args?: any): any {

    if(!value)return null;
    if(!args)return value;

    args = args.toLowerCase();

    return value.filter(function(item){
        return JSON.stringify(item).toLowerCase().includes(args);
    });
  }

}
