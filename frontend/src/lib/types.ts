export type User={id:number;email:string;role:string;loyaltyTier:string};
export type Product={id:number;sku:string;name:string;category:string;description:string;priceRwf:number;size:string;colour:string;stockQty:number;published:boolean;imageUrl?:string};
export type CartItem={id:number;product:Product;quantity:number};
export type Wish={id:number;product:Product};
export type Order={customer?:User;id:number;orderNo:string;state:string;subtotal:number;discount:number;shipping:number;total:number;paymentMethod:string;trackingNumber?:string;createdAt:string;lines:{product:Product;quantity:number;unitPrice:number}[]};
