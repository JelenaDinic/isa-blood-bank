export class Staff {
    firstName: string = "";
    lastName: string = "";
    email: string = "";
    password: string = "";
    role: string = "STAFF";
    address: string = "";
    dob!: Date;
    phoneNumber: string = "";
    bloodBankId!: number;


    public constructor(obj?: any) {
        if (obj) {
            this.firstName = obj.firstName;
            this.lastName = obj.lastName;
            this.email = obj.email;
            this.password = obj.password;
            this.role = obj.role;
            this.address = obj.address;
            this.dob = obj.dob;
            this.phoneNumber = obj.phoneNumber;
            this.bloodBankId = obj.bloodBankId;
        }
    }
}