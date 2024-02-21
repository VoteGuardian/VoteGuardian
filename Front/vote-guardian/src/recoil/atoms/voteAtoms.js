const { atom } = require("recoil");
//key : 전역적인 고유 키, default : 초기 값

//생성한 투표 목록인지 참여 가능한 투표 목록인지 판단
export const voteType = atom({
    key: 'voteType',
    default: 'create'
})
//생성한 투표나 참여 가능한 투표 목록
export const voteListState = atom({
    key: 'voteListState',
    default: []
})
//투표 상태(전체, 예정, 진행, 종료)
export const voteState = atom({
    key: 'voteState',
    default: 0
})
//불러온 투표 목록이 가지는 전체 페이지 수
export const voteTotalPage = atom({
    key: 'voteTotalPage',
    default: 0
})
//현재 페이지 번호
export const votePage = atom({
    key: 'votePage',
    default: 1
})

//아래 두 개는 voteListState로 대체된 상태
//회원이 생성한 투표 목록
// export const createVoteListState = atom({
//     key : 'createVoteListState',
//     default: []
// })

//참여할 수 있는 투표 목록
// export const participateVoteListState = atom({
//     key: 'participateVoteListState',
//     default: []
// })