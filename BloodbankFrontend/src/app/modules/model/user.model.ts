export class User{
    email: string = "";
    password: string = "";
    confirmPassword: string = "";
    gender: string = "";
    firstName: string = "";
    lastName: string = "";
    street: string = "";
    number: string = "";
    city: string = "";
    country: string = "";
    dob!: Date;
    phoneNumber: string = "";
    personalId!: number;
    profession: string = "";
    professionInfo: string = "";
    
    public constructor(obj?: any) {
        if (obj) {
            this.firstName = obj.firstName;
            this.lastName = obj.lastName;
            this.email = obj.email;
            this.password = obj.password;
            this.street = obj.street;
            this.city = obj.city;
            this.number = obj.number;
            this.country = obj.country;
            this.dob = obj.dob;
            this.phoneNumber = obj.phoneNumber;
            this.gender = obj.gender;
            this.personalId = obj.personalId;
            this.profession = obj.profession;
            this.professionInfo = obj.professionInfo;
        }
    }
}