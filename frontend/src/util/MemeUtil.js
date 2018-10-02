import {DEFAULT_COOKIE_REGEX, DEFAULT_COOKIE_VALUE} from "./TextConstant";

class MemeUtil {
    static identifyCookieByName(name) {
        let matches = document.cookie.match(new RegExp(
            `(?:^|; )${name.replace(DEFAULT_COOKIE_REGEX, '\\$1')}=([^;]*)`
        ));
        return matches ? decodeURIComponent(matches[1]).replace('|', ',') : undefined;
    }

    static deleteCookieByName(name) {
        document.cookie = name + DEFAULT_COOKIE_VALUE;
    }

    static IsoDateString(date) {
        function pad(n) {
            return n < 10 ? "0" + n : n
        }

        return date.getUTCFullYear() + "-" + pad(date.getUTCMonth() + 1) + "-" + pad(date.getUTCDate()) + "T"
            + pad(date.getUTCHours()) + ":" + pad(date.getUTCMinutes()) + ":" + pad(date.getUTCSeconds())
    }

    static findIdByUrl(regex, currentUrl){
        return  regex.exec(currentUrl)[1];
    }
}

export default MemeUtil;