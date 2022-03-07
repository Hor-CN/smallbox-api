package cn.itbk.smallbox.service

import cn.itbk.smallbox.domain.Applet
import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.service.IService

/**
 * @author Hor
 * @description 针对表【applet(拓展插件表)】的数据库操作Service
 * @createDate 2022-03-07 14:43:19
 */
interface IAppletService : IService<Applet?> {

    fun getSearchPage(search: String, currentPage: Long, pageSize: Long): IPage<Applet>

}