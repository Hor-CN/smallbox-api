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

    /**
     * 获取搜索小程序列表分页
     * @param search 搜索内容
     * @param currentPage 当前页
     * @param pageSize 每页个数
     * @author Hor
     * @return IPage<Applet>
     */
    fun findSearchAppletPage(search: String, currentPage: Long, pageSize: Long): IPage<Applet>


    /**
     * 获取小程序的详情
     * @param id 小程序ID
     * @return Applet
     */
    fun findAppletDetail(id: Long): Applet

}