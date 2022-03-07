package cn.itbk.smallbox.service.impl

import cn.itbk.smallbox.domain.Applet
import cn.itbk.smallbox.mapper.AppletMapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import cn.itbk.smallbox.service.IAppletService
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.springframework.stereotype.Service

/**
 * @author Hor
 * @description 针对表【applet(拓展插件表)】的数据库操作Service实现
 * @createDate 2022-03-07 14:43:19
 */
@Service
class AppletServiceImpl : ServiceImpl<AppletMapper, Applet>(), IAppletService {

    override fun getSearchPage(search: String, currentPage: Long, pageSize: Long): IPage<Applet> {
        val appletPage = Page<Applet>(currentPage, pageSize)
        return getBaseMapper().selectPage(appletPage,QueryWrapper<Applet?>().like("name",search))
    }


}