export class Sysadmin {
    id!: number;
    firstName: string = "";
    lastName: string = "";
    email: string = "";
    password: string = "";
    role: string = "SYSTEMADMIN";
    street: string = "";
    city: string = "";
    number: string = "";
    country: string = "";
    dob!: Date;
    phoneNumber: string = "";
    personalId!: number;
    gender: string = "";


    public constructor(obj?: any) {
        if (obj) {
            this.id = obj.id;
            this.firstName = obj.firstName;
            this.lastName = obj.lastName;
            this.email = obj.email;
            this.password = obj.password;
            this.role = obj.role;
            this.street = obj.street;
            this.city = obj.city;
            this.number = obj.number;
            this.country = obj.country;
            this.dob = obj.dob;
            this.phoneNumber = obj.phoneNumber;
        }
    }
}