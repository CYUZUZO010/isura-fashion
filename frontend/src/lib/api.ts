const API=import.meta.env.VITE_API_URL||'http://localhost:8081/api';
export function token(){return localStorage.getItem('isura_token')||''}
export async function api<T>(path:string,options:RequestInit={}){const res=await fetch(API+path,{...options,headers:{'Content-Type':'application/json',...(token()?{Authorization:`Bearer ${token()}`}:{}) ,...(options.headers||{})}});const data=await res.json().catch(()=>null);if(!res.ok)throw new Error(data?.error||'Something went wrong');return data as T}
export const money=(n:number)=>new Intl.NumberFormat('en-RW').format(n)+' RWF';
import type {Product,CartItem} from './types';
export function guestCart():CartItem[]{try{return JSON.parse(localStorage.getItem('isura_guest_cart')||'[]')}catch{return []}}
export function saveGuestCart(items:CartItem[]){localStorage.setItem('isura_guest_cart',JSON.stringify(items))}
export function addGuest(product:Product,qty:number){const items=guestCart();const old=items.find(i=>i.product.id===product.id);if(old)old.quantity=Math.min(product.stockQty,old.quantity+qty);else items.push({id:-product.id,product,quantity:qty});saveGuestCart(items);return items}
