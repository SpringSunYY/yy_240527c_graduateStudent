import request from '@/utils/request'

// 查询学生更多信息列表
export function listStudentMoreInfo(query) {
  return request({
    url: '/manage/studentMoreInfo/list',
    method: 'get',
    params: query
  })
}

// 查询学生更多信息详细
export function getStudentMoreInfo(id) {
  return request({
    url: '/manage/studentMoreInfo/' + id,
    method: 'get'
  })
}

// 新增学生更多信息
export function addStudentMoreInfo(data) {
  return request({
    url: '/manage/studentMoreInfo',
    method: 'post',
    data: data
  })
}

// 修改学生更多信息
export function updateStudentMoreInfo(data) {
  return request({
    url: '/manage/studentMoreInfo',
    method: 'put',
    data: data
  })
}

// 删除学生更多信息
export function delStudentMoreInfo(id) {
  return request({
    url: '/manage/studentMoreInfo/' + id,
    method: 'delete'
  })
}
