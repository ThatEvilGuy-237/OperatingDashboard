class Utils {
    static formatDate(date: string | null | undefined): string {
        if (!date) return "N/A";
        return new Date(date).toLocaleString();
    }

    static isLast(index: number, length: number): boolean {
        return index === length - 1;
    }
}

export default Utils
