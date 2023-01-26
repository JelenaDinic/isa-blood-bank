export class NewAppointmentDTO {
    public bloodBankCenterId!: number;
    public patientId!: number;
    public dateTime!: Date;

    public constructor(obj?: any) {
        if(obj){
            
        }
    }
}