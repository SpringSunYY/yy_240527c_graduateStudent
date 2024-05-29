import request from '@/utils/request'

// 查询支出信息列表
export function listProjectExpenditureInfo(query) {
  return request({
    url: '/manage/projectExpenditureInfo/list',
    method: 'get',
    params: query
  })
}

// 查询支出信息详细
export function getProjectExpenditureInfo(id) {
  return request({
    url: '/manage/projectExpenditureInfo/' + id,
    method: 'get'
  })
}

// 新增支出信息
export function addProjectExpenditureInfo(data) {
  return request({
    url: '/manage/projectExpenditureInfo',
    method: 'post',
    data: data
  })
}

// 修改支出信息
export function updateProjectExpenditureInfo(data) {
  return request({
    url: '/manage/projectExpenditureInfo',
    method: 'put',
    data: data
  })
}

// 删除支出信息
export function delProjectExpenditureInfo(id) {
  return request({
    url: '/manage/projectExpenditureInfo/' + id,
    method: 'delete'
  })
}
