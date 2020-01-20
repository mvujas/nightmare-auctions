import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavigationBarComponent } from './navigation-bar/navigation-bar.component';
import { FooterComponent } from './footer/footer.component';
import { FancyInputComponent } from '@app/shared/components/fancy-input/fancy-input.component';

const componentsToExport: any[] = [
  NavigationBarComponent,
  FooterComponent
];

const sharedComponentsUsed: any[] = [
  FancyInputComponent
];

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [ 
    ...componentsToExport,
    ...sharedComponentsUsed
  ],
  exports: [ ...componentsToExport ]
})
export class CoreModule { }
