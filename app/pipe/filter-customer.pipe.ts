import { Pipe, PipeTransform } from '@angular/core';
import { CustomerDetails } from '../Model/CustomerDetails';

@Pipe ({
  name: 'filter'
})
export class FilterCustomerPipe implements PipeTransform {

 /* transform(customers:CustomerDetails[], SearchTerm:any): CustomerDetails[] {
    if(!customers || !SearchTerm)
    {
      return customers;
    }
    if(typeof(SearchTerm)=="string")
    return customers.filter(customer=>customer.name.toLocaleLowerCase().match(SearchTerm.toLocaleLowerCase()));
    else if(typeof(SearchTerm)=="number")
    return customers.filter(customer=>customer.userId.valueOf()==SearchTerm);
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
