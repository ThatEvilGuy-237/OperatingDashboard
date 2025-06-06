import Cookies from 'js-cookie';
// env vars
const DOMAIN = import.meta.env.VITE_DOMAIN_NAME;
class CookieService {
  
  getCookie(name: string): string | undefined {
    return Cookies.get(name);
  }

  setCookie(name: string, value: string, days: number = 7): void {
    Cookies.set(name, value, {
      expires: days,
      path: '/',
      secure: true,
      sameSite: 'Strict',
      domain: DOMAIN,
    });
  }

  // Remove a cookie
  removeCookie(name: string): void {
    Cookies.remove(name, { path: '/' });
  }
}

export default new CookieService();