import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'home',
    loadChildren: () => import('@modules/home/home.module').then(mod => mod.HomeModule)
  },
  {
    path: 'items',
    loadChildren: () => import('@modules/items/items.module').then(mod => mod.ItemsModule)
  },
  {
    path: 'user',
    loadChildren: () => import('@modules/user/user.module').then(mod => mod.UserModule)
  },
  {
    path: 'grades',
    loadChildren: () => import('@modules/grades/grades.module').then(mod => mod.GradesModule)
  },
  {
    path: 'statistics',
    loadChildren: () => import('@modules/statistics/statistics.module').then(mod => mod.StatisticsModule)
  },
  {
    path: 'auth',
    loadChildren: () => import('@modules/authentication/authentication.module').then(mod => mod.AuthenticationModule)
  },
  {
    path: '404',
    loadChildren: () => import('@modules/page404/page404.module').then(mod => mod.Page404Module)
  },
  {
    path: '**',
    redirectTo: '404'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
