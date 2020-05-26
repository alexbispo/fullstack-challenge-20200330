import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from '../product/product';
import { ProductsListService } from '../products-list/products-list.service';

@Component({
  templateUrl: './dashboard.component.html'
})
export class DashBoardComponent implements OnInit {

  products: Product[] = [];

  constructor(
    private activatedRoute: ActivatedRoute,
    private productsListService: ProductsListService
  ) { }

  ngOnInit(): void {
    this.products = this.activatedRoute.snapshot.data['products'];
  }

  handleUploadCompleted(success) {
    console.log(success);
    if (success) {
      this.productsListService.execute().subscribe(products => {
        this.products = this.products.concat(products);
      });

    }
  }

}
