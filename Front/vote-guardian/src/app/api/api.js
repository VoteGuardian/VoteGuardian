// import {NextResponse} from "next/server";

// const BASE_URL = 'http://localhost:8080';
// export async function GET() {

// }
//import axios from "axios";
//
// const BASE_URL = '';
//
// let token = window.localStorage.getItem("token");
// if (token == null) {
//     token = "";
// }
// axios.defaults.withCredentials = true;
// const api = axios.create({
//     baseURL: HOST,
//     headers: { Authorization: `Bearer ${token}` },
// });
//
// export default api;

// export default api;

// export async function fetchCoinsPrice(id?: string | undefined) {
//     const data = await axios.get(`${BASE_URL}/tickers/${id ? id : ''} `);
//     return data.data;
//   }
  
//   export async function fetchCoinsInfo(id: string) {
//     const data = await axios.get(`${BASE_URL}/coins/${id}`);
//     return data.data;
//   }
  
//   export async function fetchCoinHistory(id: string) {
//     console.log(id);
  
//     const data = await axios.get(
//       `https://ohlcv-api.nomadcoders.workers.dev/?coinId=${id}`
//     );
//     return data.data;
//   }