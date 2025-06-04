import Cookies from 'js-cookie';

class CookieUtility {
  getCookie(name: string): string | undefined {
    return Cookies.get(name);
  }

  setCookie(name: string, value: string, days: number = 7): void {
    Cookies.set(name, value, { expires: days, path: '/', secure: true, sameSite: 'Strict' });
  }

  // Remove a cookie
  removeCookie(name: string): void {
    Cookies.remove(name, { path: '/' });
  }
}

export default new CookieUtility();