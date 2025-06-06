import EvilApiService from "./EvilService";
import CookieService from "./CookieService";

class AuthService extends EvilApiService {
  static async login(identifier: string, password: string): Promise<boolean> {
    console.log('API URL:', import.meta.env.VITE_API_URL);
    try {
      const response = await this.apiClient.post('/auth/login', { identifier, password });
      CookieService.setCookie('token', response.data.token, 7);
      return true;
    } catch (error) {
      console.warn('Login failed:', error);
      return false;
    }
  }


  static isTokenExpired(token: string): boolean {
    const payload = JSON.parse(atob(token.split('.')[1]));
    if (!payload.exp) return true;
    // exp is in seconds, Date.now() is in ms
    return Date.now() >= payload.exp * 1000;
  }

  static async validateToken(): Promise<boolean> {
    const token = CookieService.getCookie('token');
    if (!token) {
      console.warn('No token found in cookies');
      return false;
    }
    if (this.isTokenExpired(token)) {
      console.warn('Token is expired');
      return false;
    }
    const response = await this.apiClient.get('/validate', {
      headers: { Authorization: `Bearer ${token}` }
    });
    // redirect to dashboard if token is valid
    return response.data;
  }
}

export default AuthService;