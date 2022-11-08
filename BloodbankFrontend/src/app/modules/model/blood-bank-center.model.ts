export class BloodBankCenter {
    id: number = 0;
    name: string = "";
    address: string = "";
    description: string = "";
    averageGrade: number = 0;


    public constructor(obj?: any) {
        if (obj) {
            this.id = obj.id;
            this.name = obj.name;
            this.address = obj.address;
            this.description = obj.description;
            this.averageGrade = obj.averageGrade;
             
        }
    }
}