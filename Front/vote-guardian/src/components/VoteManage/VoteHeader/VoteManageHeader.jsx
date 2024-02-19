import './VoteManageHeader.scss';
import { useRecoilState } from 'recoil';
import { voteState } from "@/recoil/atoms/createVoteListState";
import { useEffect } from 'react';
import { Mobile, PC, Tablet } from '@/hooks/useResize';

export default function VoteManageHeader() {
    const ENTIRE = 0;
    const SCHEDULE = 1;
    const PROGRESS = 2;
    const END = 3;

    const [currentVoteState, setCurrentVoteState] = useRecoilState(voteState);

    //페이지가 바뀔 때마다 실행
    useEffect(() => {
        //투표의 상태를 선택할 수 있는 버튼 목록
        const voteStateList = document.getElementsByClassName("vote-state-list")[0].childNodes;
        for(let i = 0; i < voteStateList.length; i++) {
            voteStateList[i].classList.remove('selected');
        }
        voteStateList[currentVoteState].classList.add('selected');
    }, [currentVoteState]);

    //전체를 눌렀을 때 모든 투표 목록을 불러오기
    function handleEntireList() {
        setCurrentVoteState(ENTIRE);
    }
    //예정을 눌렀을 때 투표가 예정 상태인 목록으로 새로 불러오기(1)
    function handleScheduleList() {
        setCurrentVoteState(SCHEDULE);
    }
    //진행을 눌렀을 때 진행중인 투표 목록으로 새로 불러오기(2)
    function handleProgressList() {
        setCurrentVoteState(PROGRESS);
    }
    //종료를 눌렀을 때 투표가 종료된 목록으로 새로 불러오기
    function handleEndList() {
        setCurrentVoteState(END);
    }

    return (
        <>
            <PC>
                <div className='pc-vote-header-wrap'>
                    <div className='vote-header-title'>
                        <p>투표 관리하기</p>
                    </div>
                    <div className='vote-state-list-wrap'>
                        <ul className='vote-state-list'>
                            <li onClick={handleEntireList}>전체</li>
                            <li onClick={handleScheduleList}>예정</li>
                            <li onClick={handleProgressList}>진행</li>
                            <li onClick={handleEndList}>종료</li>
                        </ul>
                    </div>
                </div>
            </PC>
            <Tablet>
                <div className='tablet-vote-header-wrap'>
                    <div className='vote-header-title'>
                        <p>투표 관리하기</p>
                    </div>
                    <div className='vote-state-list-wrap'>
                        <ul className='vote-state-list'>
                            <li onClick={handleEntireList}>전체</li>
                            <li onClick={handleScheduleList}>예정</li>
                            <li onClick={handleProgressList}>진행</li>
                            <li onClick={handleEndList}>종료</li>
                        </ul>
                    </div>
                </div>
            </Tablet>
            <Mobile>
                <div className='mobile-vote-header-wrap'>
                    <div className='vote-header-title'>
                        <p>투표 관리하기</p>
                    </div>
                    <div className='vote-state-list-wrap'>
                        <ul className='vote-state-list'>
                            <li onClick={handleEntireList}>전체</li>
                            <li onClick={handleScheduleList}>예정</li>
                            <li onClick={handleProgressList}>진행</li>
                            <li onClick={handleEndList}>종료</li>
                        </ul>
                    </div>
                </div>
            </Mobile>
        </>
    )
}