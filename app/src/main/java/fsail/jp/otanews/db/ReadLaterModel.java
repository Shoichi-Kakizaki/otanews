package fsail.jp.otanews.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by kakizaki_shoichi on 2015/07/14.
 */

@Table(name = "ReadLaterModel")
public class ReadLaterModel extends Model {
    @Column(name = "title", notNull = true)
    public String title;

    @Column(name = "uri", notNull = true)
    public String uri;

    public ReadLaterModel() {
        super();
    }
}
