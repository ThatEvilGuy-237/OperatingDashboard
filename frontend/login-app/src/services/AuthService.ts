import EvilApiService from "./EvilService";
import CookieService from "./CookieService";

class AuthService extends EvilApiService {
  static async login(username: string, password: string): Promise<boolean> {
    try {
      const response = await this.apiClient.post('/auth/login', { username, password });
      CookieService.setCookie('token', response.data.token, 7);
      return true;
    } catch (error) {
      console.error('Login failed:', error);
      throw error;
    }
  }
}

export default AuthService;