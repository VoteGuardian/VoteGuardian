import './VoteManageHeader.scss';
import { useSetRecoilState } from 'recoil';
import { createVoteListState } from "@/recoil/atoms/createVoteListState";

export default function VoteManageHeader() {
    const ENTIRE = 0;
    const SCHEDULE = 1;
    const PROGRESS = 2;
    const END = 3;

    const setVoteManageList = useSetRecoilState(createVoteListState);

    //전체를 눌렀을 때 모든 투표 목록을 불러오기
    function handleEntireList() {
        //api..요청을 하나?
        console.log('전체')
        setVoteManageList([
            {
                id: 1, title:"OO대학교 학생회장 선거", startAt: '2023-04-15 11:00:00',
                finishAt: '2023-04-31 23:59:59', createdAt: '2023-04-01 10:23:42', state: 1
            },
            {
                id: 2, title:"OO대학교 XX학과 MT 날짜 투표", startAt: '2023-04-15 11:00:00',
                finishAt: '2023-04-31 23:59:59', createdAt: '2023-04-01 10:23:42', state: 2
            },
            {
                id: 3, title:"굉장히 긴 제목이 하나 필요한데 뭐라고 적을까", startAt: '2023-03-12 09:00:00',
                finishAt: '2023-03-12 11:59:59', createdAt: '2023-03-12 08:23:13', state: 3
            },
            {
                id: 4, title:"굉장히 긴 제목이 하나 필요한데 뭐라고 적을까", startAt: '2023-03-12 09:00:00',
                finishAt: '2023-03-12 11:59:59', createdAt: '2023-03-12 08:23:13', state: 3
            }
        ])
    }
    //예정을 눌렀을 때 투표가 예정 상태인 목록으로 새로 불러오기(0)
    function handleScheduleList() {
        setVoteManageList([
            {
                id: 1, title:"OO대학교 학생회장 선거", startAt: '2023-04-15 11:00:00',
                finishAt: '2023-04-31 23:59:59', createdAt: '2023-04-01 10:23:42', state: 1
            }
        ])
    }
    //진행을 눌렀을 때 진행중인 투표 목록으로 새로 불러오기(1)
    function handleProgressList() {
        setVoteManageList([
            {
                id: 2, title:"OO대학교 XX학과 MT 날짜 투표", startAt: '2023-04-15 11:00:00',
                finishAt: '2023-04-31 23:59:59', createdAt: '2023-04-01 10:23:42', state: 2
            }
        ])
    }
    //종료를 눌렀을 때 투표가 종료된 목록으로 새로 불러오기
    function handleEndList() {
        setVoteManageList([
            {
                id: 3, title:"굉장히 긴 제목이 하나 필요한데 뭐라고 적을까", startAt: '2023-03-12 09:00:00',
                finishAt: '2023-03-12 11:59:59', createdAt: '2023-03-12 08:23:13', state: 3
            },
            {
                id: 4, title:"굉장히 긴 제목이 하나 필요한데 뭐라고 적을까", startAt: '2023-03-12 09:00:00',
                finishAt: '2023-03-12 11:59:59', createdAt: '2023-03-12 08:23:13', state: 3
            }
        ])
    }

    return (
        <>
            <div className="vote-header-title">
                <p>투표 관리하기</p>
            </div>
            <div className='vote-type-list'>
                <ul>
                    <li onClick={handleEntireList}>전체</li>
                    <li onClick={handleProgressList}>진행</li>
                    <li onClick={handleScheduleList}>예정</li>
                    <li onClick={handleEndList}>종료</li>
                </ul>
            </div>
        </>
    )
}