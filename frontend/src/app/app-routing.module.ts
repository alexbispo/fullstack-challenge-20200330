import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashBoardComponent } from './dashboard/dashboard.component';
import { ProductsResolver } from './dashboard/products.resolver';
import { ProductEditComponent } from './product-edit/product-edit.component';


const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: DashBoardComponent,
    resolve: {
      products: ProductsResolver
    }
  },
  {
    path: 'products/:productId/edit',
    component: ProductEditComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
