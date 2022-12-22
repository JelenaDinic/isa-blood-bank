export class BloodBankCenter {
    id: number = 0;
    name: string = "";
    street: string = "";
    city: string = "";
    number: string = "";
    country: string = "";
    description: string = "";
    averageGrade: number = 0;


    public constructor(obj?: any) {
        if (obj) {
            this.id = obj.id;
            this.name = obj.name;
            this.street = obj.street;
            this.city = obj.city;
            this.number = obj.number;
            this.country = obj.country;
            this.description = obj.description;
            this.averageGrade = obj.averageGrade; 
        }
    }
}