package cn.itbk.smallbox.service.impl

import cn.itbk.smallbox.common.ResultEnum
import cn.itbk.smallbox.common.SmallBoxException
import cn.itbk.smallbox.domain.Applet
import cn.itbk.smallbox.mapper.AppletMapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import cn.itbk.smallbox.service.IAppletService
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.springframework.stereotype.Service

/**
 * @作者 Hor
 * @描述 针对表【applet(拓展插件表)】的数据库操作Service实现
 * @创建时间 2022-03-07 14:43:19
 */
@Service
class AppletServiceImpl : ServiceImpl<AppletMapper, Applet>(), IAppletService {

    /**
     * 获取搜索小程序列表
     * @param search 搜索内容
     * @param currentPage 当前页
     * @param pageSize 每页个数
     * @author Hor
     * @return IPage<Applet>
     */
    override fun findSearchAppletPage(search: String, currentPage: Long, pageSize: Long): IPage<Applet> {
        return getBaseMapper().selectPage(Page(currentPage, pageSize),QueryWrapper<Applet?>().like("name",search))
    }

    /**
     * 获取小程序的详情
     * @param id 小程序ID
     * @throws SmallBoxException 小程序不存在
     * @return Applet
     */
    override fun findAppletDetail(id: Long): Applet {
        return getBaseMapper().selectById(id) ?: throw SmallBoxException("小程序不存在")
    }


}