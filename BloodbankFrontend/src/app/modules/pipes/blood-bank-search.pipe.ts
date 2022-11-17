import { Pipe, PipeTransform } from '@angular/core';
import { BloodBankDisplayDTO } from '../dto/bloodBankDisplayDTO';
import { MatTableDataSource } from '@angular/material/table';
import { BloodBankCenter } from '../model/blood-bank-center.model';

@Pipe({
  name: 'bloodBankSearch'
})
export class BloodBankSearchPipe implements PipeTransform {

    transform(value: Array<BloodBankDisplayDTO>, filterText: string[]): any {

        if(!filterText[0] && !filterText[1]){
            return value;
        }
        let temp : Array<BloodBankDisplayDTO> = []
        for(let center of value){
            if(!filterText[0] || center.name.toLowerCase().includes(filterText[0].toLowerCase())){
                if(!filterText[1] || center.city.toLowerCase().includes(filterText[1].toLowerCase())){
                    temp.push(center);
                }
            }
        }
        return temp;
    }


}
