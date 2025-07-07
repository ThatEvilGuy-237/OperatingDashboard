import axios, { type AxiosInstance } from 'axios';
import CookieService from './CookieService';


class EvilApiService {
  protected static apiClient: AxiosInstance = axios.create({
    baseURL: import.meta.env.VITE_API_URL,
    timeout: 5000,
    headers: {
      'Content-Type': 'application/json',
    },
  });

    static getHeader() {
    const token = CookieService.getCookie('token');
    return {
      Authorization: token ? `Bearer ${token}` : '',
    };
  }

}

export default EvilApiService;