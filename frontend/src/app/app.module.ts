import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpModule } from '@angular/http';
import { LoginModule} from './login/login.module';
import { CoreModule} from './core/core.module';
import { RouterModule, Routes } from '@angular/router'; 
import { BulletinBoardModule} from './bulletin-board/bulletin-board.module';
import { MapModule} from './map/map.module';
import { HomeModule} from "./home";


import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NavbarComponent } from './navbar/navbar.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { ToolsComponent } from './tools/tools.component';
import { LoginComponent } from './login/login.component';
import { QuarterService} from "./quarters";
import { LoginGuard} from './core/login.guard';



import { AUTH_PROVIDERS }      from 'angular2-jwt';

const appRoutes: Routes = [
  { path: 'dashboard', component: DashboardComponent },
  { path: '', component: AppComponent }
]; 

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    NavbarComponent,
    SidebarComponent,
    ToolsComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    LoginModule,
    CoreModule,
    MapModule,
    BulletinBoardModule,
    HomeModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [
    LoginGuard,
    QuarterService,
    AUTH_PROVIDERS
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
