import { BloodBankCenter } from "./blood-bank-center.model";
import { User } from "./user.model";

export class AppointmentDisplay {
    id!: number;
    dateTime!: string;
    duration: number = 0;
    status: string = "";
    registeredUser!: User;
    bloodBankCenter!: BloodBankCenter;

    public constructor(obj?: any) {
        if (obj) {
            this.id = obj.id;
            this.dateTime = obj.dateTime;
            this.duration = obj.duration;
            this.status = obj.status; 
            this.registeredUser = obj.registeredUser; 
            this.bloodBankCenter = obj.bloodBankCenter;      
            
        }
    }
}