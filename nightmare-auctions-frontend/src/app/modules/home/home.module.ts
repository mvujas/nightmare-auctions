import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { HomeRoutingModule } from './home-routing.module';
import { TimelineComponent } from './components/timeline/timeline.component';

const components: any[] = [
  HomePageComponent,
  TimelineComponent
];

@NgModule({
  imports: [
    CommonModule,
    HomeRoutingModule
  ],
  declarations: [ 
    ...components
  ]
})
export class HomeModule { }
