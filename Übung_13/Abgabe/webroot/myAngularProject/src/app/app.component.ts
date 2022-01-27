import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';

export interface BackendResponse {
  ip: string[];
  date: string;
  time: string;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'myAngularProject';

  date: Date | undefined;
  serverResponse: BackendResponse | undefined;

  constructor(private http: HttpClient) {
    // intentionally empty
  }

  onDateChange(event: MatDatepickerInputEvent<any>) {
    this.date = event.value;

    this.http.get<BackendResponse>('http://localhost:8080/host-date-info').subscribe(response => this.serverResponse = response);
  }
}
