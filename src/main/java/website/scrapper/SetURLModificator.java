package website.scrapper;

import java.util.HashSet;
import java.util.Set;

public class SetURLModificator {

    //Trys to remove all links that are associated with any kind of social media
    public static Set<String> RemoveSocialMediaLinks(Set<String> links){
        Set<String> socialfreeList = new HashSet<>();
        for (String link: links){
            int checksum = 0;

            if(!link.contains(".facebook.") || !link.contains(".Facebook.")){
                checksum++;
            }
            else if(!link.contains(".youtube.") || !link.contains(".Youtube.")){
                checksum++;
            }
            else if(!link.contains(".whatsapp.") || !link.contains(".Whatsapp.")){
                checksum++;
            }
            else if(!link.contains(".instagram.") || !link.contains(".Instagram.")){
                checksum++;
            }
            else if(!link.contains(".tiktok.") || !link.contains(".Tiktok.")){
                checksum++;
            }
            else if(!link.contains(".snapchat.") || !link.contains(".Snapchat.")){
                checksum++;
            }
            else if(!link.contains(".telegram.") || !link.contains(".Telegram.")){
                checksum++;
            }
            else if(!link.contains(".linkedin.") || !link.contains(".LinkedIn.")){
                checksum++;
            }
            else if(!link.contains(".xing.") || !link.contains(".Xing.")){
                checksum++;
            }

            if(checksum == 9){
                socialfreeList.add(link);
            }
        }

        return socialfreeList;
    }
}
