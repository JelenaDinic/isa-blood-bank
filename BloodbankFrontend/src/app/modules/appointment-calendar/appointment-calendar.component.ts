import { OnInit } from '@angular/core';
import {
  Component,
  ChangeDetectionStrategy,
  ViewChild,
  TemplateRef,
} from '@angular/core';
import {
  isSameDay,
  isSameMonth,
} from 'date-fns';
import { Subject } from 'rxjs';
import {
  CalendarEvent,
  CalendarEventAction,
  CalendarEventTimesChangedEvent,
  CalendarView,
} from 'angular-calendar';
import { EventColor } from 'calendar-utils';
import { AppointmentService } from '../services/appointment.service';

const colors: Record<string, EventColor> = {
  red: {
    primary: '#ad2121',
    secondary: '#FAE3E3',
  },
  blue: {
    primary: '#1e90ff',
    secondary: '#D1E8FF',
  },
  yellow: {
    primary: '#e3bc08',
    secondary: '#FDF1BA',
  },
};

@Component({
  selector: 'app-appointment-calendar',
  templateUrl: './appointment-calendar.component.html',
  styleUrls: ['./appointment-calendar.component.css']
})
export class AppointmentCalendarComponent implements OnInit {
  @ViewChild('modalContent', { static: true }) modalContent: TemplateRef<any> | undefined;

  view: CalendarView = CalendarView.Month;

  CalendarView = CalendarView;

  viewDate: Date = new Date();

  month: Date = new Date();

  modalData: {
    action: string;
    event: CalendarEvent;
  } | undefined;

  refresh = new Subject<void>();

  events!: CalendarEvent[];

  activeDayIsOpen: boolean = true;

  constructor(private appointmentService: AppointmentService) {}

  dayClicked({ date, events }: { date: Date; events: CalendarEvent[] }): void {
    if (isSameMonth(date, this.viewDate)) {
      if (
        (isSameDay(this.viewDate, date) && this.activeDayIsOpen === true) ||
        events.length === 0
      ) {
        this.activeDayIsOpen = false;
      } else {
        this.activeDayIsOpen = true;
      }
      this.viewDate = date;
    }
  }

  eventTimesChanged({
    event,
    newStart,
    newEnd,
  }: CalendarEventTimesChangedEvent): void {
    this.events = this.events.map((iEvent) => {
      if (iEvent === event) {
        return {
          ...event,
          start: newStart,
          end: newEnd,
        };
      }
      return iEvent;
    });
  }

  public changeMonth(): void {
    console.log(this.viewDate)
    console.log(this.month)
    this.viewDate = new Date(this.month)
  }


  setView(view: CalendarView) {
    this.view = view;
  }

  closeOpenMonthViewDay() {
    this.activeDayIsOpen = false;
  }

  ngOnInit(): void {

    let token = localStorage.getItem('token');
    let bloodBankCenterId;
    if(token != null) {
      let decodedJWT = JSON.parse(window.atob(token.split(".")[1]))
      bloodBankCenterId = decodedJWT.bloodBankCenterId;
    }

    this.appointmentService.findByBloodBank(bloodBankCenterId).subscribe(
      (response: CalendarEvent[]) => {
        response.forEach(element => {
          element.start = new Date(element.start);
          element.end = new Date(element.start.getTime() + 30*60000);
        });
        this.events = response;
        console.log(this.events)
      },
      (error) => {
        console.log(error.message);
      }
     );
  }

}
