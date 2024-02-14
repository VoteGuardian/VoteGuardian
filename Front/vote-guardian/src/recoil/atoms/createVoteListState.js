const { atom } = require("recoil");

//회원이 생성한 투표 목록
export const createVoteListState = atom({
    //key : 전역적인 고유 키, default : 초기 값
    key : 'createVoteListState',
    default: []
})
//투표 상태
export const voteState = atom({
    key: 'voteState',
    default: 0
})
//전체 페이지 수
export const voteTotalPage = atom({
    key: 'voteTotalPage',
    default: 0
})
//현재 페이지
export const votePage = atom({
    key: 'votePage',
    default: 1
})