export class ScheduleAppointmentDTO {
    public id: number = 0;
    public customerId :number = 0;

    public constructor(obj?: any) {
        if(obj){
            this.customerId = obj.customerId;
            this.id = obj.id;
        }
    }
}