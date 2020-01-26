import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.scss']
})
export class MessageComponent implements OnInit {

  @Input()
  private text: string;
  @Input()
  private date: Date;
  @Input()
  private received: boolean;

  constructor() { }

  ngOnInit() {
    this.text = this.text || 'Blank message';
    this.received = this.received || false;
  }

}
