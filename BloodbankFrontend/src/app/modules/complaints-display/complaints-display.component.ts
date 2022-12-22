import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { ComplaintDisplayDTO } from '../dto/complaintDisplayDTO';
import { ComplaintService } from '../services/complaint.service';

@Component({
  selector: 'app-complaints-display',
  templateUrl: './complaints-display.component.html',
  styleUrls: ['./complaints-display.component.css']
})
export class ComplaintsDisplayComponent implements OnInit{
  public complaints: ComplaintDisplayDTO[] = []
  public displayedColumns = ['user', 'text', 'regarding', 'replyButton'];
  public isReplyClicked: boolean = false;
  public complaintForReply!: ComplaintDisplayDTO;
  public replyText: string = "";

  constructor(private complaintService: ComplaintService, private toastr: ToastrService) {}


  ngOnInit(): void {
    this.complaintService.getAll().subscribe(
      (response: ComplaintDisplayDTO[]) => {
        this.complaints = response;
        console.log(this.complaints)
      },
      (error) => {
        console.log(error.message);
      }
     );
  }

  public openReplyDialogue(complaint: ComplaintDisplayDTO): void {
    this.isReplyClicked = true;
    this.complaintForReply = complaint;
  }

  public reply(): void {
    this.isReplyClicked = false;

    this.complaintService.reply(this.replyText, this.complaintForReply.id).subscribe(res => 
      {
        this.ngOnInit();
        this.toastr.success("Reply sent to users email!")
      }, (error) => {
        console.log(error)
      });
  }
}
