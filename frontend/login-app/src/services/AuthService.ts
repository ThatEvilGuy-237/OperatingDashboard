import EvilApiService from "./EvilService";
import CookieService from "./CookieService";

class AuthService extends EvilApiService {
  static async login(username: string, password: string): Promise<boolean> {
    try {
      const response = await this.apiClient.post('/auth/login', { username, password });
      console.log('Login successful:', response.data);
      CookieService.setCookie('token', response.data.token, 7);
      console.log('Token saved in cookies:', CookieService.getCookie('token'));
      return true;
    } catch (error) {
      console.error('Login failed:', error);
      throw error;
    }
  }
}

export default AuthService;