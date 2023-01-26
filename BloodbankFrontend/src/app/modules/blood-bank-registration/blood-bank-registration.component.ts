import { Component, OnInit } from '@angular/core';
import { BloodBankCenter } from '../model/blood-bank-center.model';
import { BloodBankService } from '../services/blood-bank-center.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-blood-bank-registration',
  templateUrl: './blood-bank-registration.component.html',
  styleUrls: ['./blood-bank-registration.component.css']
})
export class BloodBankRegistrationComponent implements OnInit {

  public bloodBankCenter : BloodBankCenter = new BloodBankCenter();
  public errorMessage: Error = new Error;
  public errorMap: Map<string, string> = new Map();

  constructor(private bloodBankService: BloodBankService, private router: Router) { }

  ngOnInit(): void {
  }

  public registerBloodBank() {

    if(this.isInputValid()) {
      this.bloodBankService.create(this.bloodBankCenter).subscribe(res => {
        this.router.navigate(['/']);
      }, (error) => {
        console.log(error)
        this.errorMessage = error;
        this.toastError();
      });
    }
    
  }

  public isInputValid(): boolean {
    if (this.bloodBankCenter.name == '' || this.bloodBankCenter.description == '' || this.bloodBankCenter.averageGrade == null || this.bloodBankCenter.street == ''
    || this.bloodBankCenter.number == '' || this.bloodBankCenter.city == '' || this.bloodBankCenter.country == '') {
      console.log(this.bloodBankCenter)
      alert('Please fill in all fields!');
      return false;
    }

    if(!this.startsWithCapital(this.bloodBankCenter.name)) {
      alert('Name must start with a capital letter!');
      return false;
    }

    if(!this.startsWithCapital(this.bloodBankCenter.street)) {
      alert('Street must start with a capital letter!');
      return false;
    }

    if(!this.startsWithCapital(this.bloodBankCenter.city)) {
      alert('City must start with a capital letter!');
      return false;
    }

    if(!this.startsWithCapital(this.bloodBankCenter.country)) {
      alert('Country must start with a capital letter!');
      return false;
    }

    if(!this.bloodBankCenter.number.match("([0-9]{1,3})[A-Z]?")) {
      alert('Invalid street number input!');
      return false;
    }


    return true;
  }

  public startsWithCapital(word: string){
    return word.charCodeAt(0) >= 65 && word.charCodeAt(0) <= 90
  }

  private toastError() {
    if (String(this.errorMessage).includes('406')){
      var error = localStorage.getItem('errormap')!;
      this.errorMap = new Map(JSON.parse(error));

      for (let entry of this.errorMap.entries()) {
        alert('Validation error: ' + entry[1]);
      }
    }
    else{
      alert(this.errorMessage.message);
    }
  }

}
