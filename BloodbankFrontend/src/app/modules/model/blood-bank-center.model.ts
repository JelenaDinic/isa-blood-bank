export class BloodBankCenter {
    name: string = "";
    address: string = "";
    description: string = "";
    averageGrade: number = 0 


    public constructor(obj?: any) {
        if (obj) {
            this.name = obj.name;
            this.address = obj.address;
            this.description = obj.description;
            this.averageGrade = obj.averageGrade;
             
        }
    }
}