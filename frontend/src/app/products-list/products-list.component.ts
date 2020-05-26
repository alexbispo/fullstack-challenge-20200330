import { Component, Input, SimpleChanges } from '@angular/core';
import { Product } from '../product/product';
import { DeleteProductService } from './delete-product.service';

@Component({
  templateUrl: './products-list.component.html',
  selector: 'fsc-products-list'
})
export class ProductListComponent {

  @Input() products: Product[] = [];

  constructor(private deleteProductService: DeleteProductService) {}

  delete(productId: string) {
    if(confirm('Deseja realmente remover o produto?')){
      this.deleteProductService
        .execute(productId)
        .subscribe(
          () => {
            this.products = this.products.filter(product => product.id !== productId);
          },
          err => {
            console.log(err);
            alert('Ocorreu um erro ao tentar remover o produto. Por favor tente novamente mais tarde.')
          }
        );
    }
  }

}
