import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpModule } from '@angular/http';
import { LoginModule} from './login/login.module';
import { CoreModule} from './core/core.module';
import { RouterModule, Routes } from '@angular/router'; 
import { BulletinBoardModule} from './bulletin-board/bulletin-board.module';

import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NavbarComponent } from './navbar/navbar.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { ToolsComponent } from './tools/tools.component';


import { AUTH_PROVIDERS }      from 'angular2-jwt';

const appRoutes: Routes = [
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
    BulletinBoardModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [
    AUTH_PROVIDERS
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
