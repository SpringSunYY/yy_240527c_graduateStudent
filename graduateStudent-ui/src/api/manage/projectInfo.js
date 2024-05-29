import request from '@/utils/request'

// 查询项目信息列表
export function listProjectInfo(query) {
  return request({
    url: '/manage/projectInfo/list',
    method: 'get',
    params: query
  })
}

export function listProjectInfoAll(query) {
  return request({
    url: '/manage/projectInfo/listAll',
    method: 'get',
    params: query
  })
}

// 查询项目信息详细
export function getProjectInfo(id) {
  return request({
    url: '/manage/projectInfo/' + id,
    method: 'get'
  })
}

// 新增项目信息
export function addProjectInfo(data) {
  return request({
    url: '/manage/projectInfo',
    method: 'post',
    data: data
  })
}

// 修改项目信息
export function updateProjectInfo(data) {
  return request({
    url: '/manage/projectInfo',
    method: 'put',
    data: data
  })
}

// 删除项目信息
export function delProjectInfo(id) {
  return request({
    url: '/manage/projectInfo/' + id,
    method: 'delete'
  })
}
