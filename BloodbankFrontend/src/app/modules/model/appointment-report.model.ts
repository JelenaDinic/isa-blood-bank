export class AppointmentReport {
    id!: number;
    registeredUserId!: number;
    appointmentId: number = 0;
    bloodType!: number;
    hand: string = "";
    amount: number = 0;
    copperSulfate: string = ""
    hemoglobinometer: number = 0


    public constructor(obj?: any) {
        if (obj) {
            this.id = obj.id;
            this.registeredUserId = obj.registeredUserId;
            this.appointmentId = obj.appointmentId;
            this.hand = obj.hand;
            this.amount = obj.amount;
            this.bloodType = obj.bloodType;
            this.copperSulfate = obj.copperSulfate
            this.hemoglobinometer = obj.hemoglobinometer
        }
    }
}